require 'rspec'
require "TP1/Metaprogramming"

describe 'Method adding in different situations' do

  before(:all) do

  end

  it 'should add behaviour after setting prototypes' do
    @obj = Object.new
    @obj.extend TP::Prototyped
    @another_obj = Object.new
    @another_obj.extend TP::Prototyped

    @another_obj.set_prototype(@obj)
    @obj.set_method(:a, proc{"a"})

    module B
      def b
        50
      end
    end

    @obj.singleton_module.include B
    expect(@obj.b).to eq(50)

    expect(@another_obj.a).to eq("a")
    #expect(@another_obj.b).to eq(50)
  end

  it 'should add behaviour with classes' do

    class Obj
      include TP::Prototyped
    end

    @obj = Obj.new
    @another_obj = Obj.new

    @another_obj.set_prototype(@obj)
    @obj.set_method(:a, proc{"a"})

    module B
      def b
        50
      end
    end

    @obj.singleton_module.include B
    expect(@obj.b).to eq(50)

    expect(@another_obj.a).to eq("a")
    #expect(@another_obj.b).to eq(50)
  end
end