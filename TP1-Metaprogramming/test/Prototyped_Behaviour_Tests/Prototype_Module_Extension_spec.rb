require 'rspec'

describe 'Extend Singleton Module Behaviour' do

  it 'Should extend local class behaviour' do

    class LocalClass
      include TP::Prototyped
    end

    expect(LocalClass.include?(TP::Prototyped)).to eq(true)

    expect(LocalClass.method_defined?(:method_missing)).to eq(true)

  end


end