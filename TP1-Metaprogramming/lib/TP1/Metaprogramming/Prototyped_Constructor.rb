require "TP1/Prototyped"
require "TP1/Singleton_Module_Behaviour"

module TP

  module PrototypedConstructor

    attr_accessor :constructor, :prototype

    def initialize(instance, block)
      @prototype = instance
      @constructor = block
    end

    def new(*args)
      new_object = Object.new
      new_object.extend Prototyped

      new_object.set_prototype(@prototype)
      @constructor.call(new_object, *args)
      return new_object
    end

  end

end